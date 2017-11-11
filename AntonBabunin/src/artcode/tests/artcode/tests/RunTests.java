package artcode.tests;


public class RunTests {

//    Tests CalculateAmountPrice()

//    @Test
//    public void testCalculateAmountPrice() {
//        Bill bill12 = new Bill();
//        Product product1 = new Product(1);
//        product1.setPrice(1.1);
//        product1.setName("asd");
//        product1.setId(1);
//
//        Product product2 = new Product(2);
//        product2.setPrice(2.2);
//        product2.setName("as45d");
//        product1.setId(2);
//
//        Product product3 = new Product(3);
//        product3.setPrice(2.5);
//        product3.setName("as12d");
//        product1.setId(3);
//
//        bill12.addProduct(product1);
//        bill12.addProduct(product2);
//        bill12.addProduct(product3);
//        double y = 0.0;
//        for (Product x : bill12.getProducts()) {
//            if (x != null) {
//                y = y + x.getPrice();
//            } else {
//                y = y;
//            }
//        }
//        double expected = 5.8;
//        double actual = bill12.closeBill();
//        Assert.assertEquals(expected,actual,0.001);
//    }

    //    Tests CalculateAmountPrice()

}























    /*
    @Test
    public void testGetPrice1(){
        Product product = new Product("asd", 1.11, 1);
        double expected = 1.11;
        double actual = product.getPrice();
        Assert.assertEquals(expected, actual, 0);
    }
    @Test
    public void testAddProduct1(){
        Bill bill = new Bill();
        Product expected = new Product("asd", 123.22, 123);
        bill.addProduct(expected);
        Product actual = bill.getProducts()[0];
        Assert.assertSame(expected, actual);
    }
    @Test
    public void testAddProduct2(){
        Bill bill = new Bill();
        Product expected = new Product(null, 123.22, 123);
        bill.addProduct(expected);
        Product actual;
        if (bill != null && expected.getName() != null) {
            actual = bill.getProducts()[0];
        } else {
            actual = null;
        }
        Assert.assertNull(actual);
    }

    @Test
    public void testDelFromBill(){

        Bill bill = new Bill();
        Product product1 = new Product("Notebook", 1.1, 1);
        Product product2 = new Product("Smart", 1.1, 2);
        Product product3 = new Product("Tablet", 1.1, 3);
        Product product4 = new Product("Delete", 1.1, 4);
        Product product5 = new Product("Pain", 1.1, 5);
        bill.addProduct(product1);
        bill.addProduct(product2);
        bill.addProduct(product3);
        bill.addProduct(product4);
        bill.addProduct(product5);
        bill.delFromBill(product4);
//        for (Product x : bill.getProducts()){
//            if (x != null) {
//                System.out.println(x.getName());
//            } else {
//                System.out.println("null");
//            }
//        }
        String[] expected = {"Notebook","Smart", "Tablet", null, "Pain", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null};
        String[] actual = new String[20];
        for (int i = 0; i < bill.getProducts().length; i++) {
            if (bill.getProducts()[i] != null) {
                actual[i] = bill.getProducts()[i].getName();
            }
        }
        Assert.assertArrayEquals(expected, actual);
    }

 */
