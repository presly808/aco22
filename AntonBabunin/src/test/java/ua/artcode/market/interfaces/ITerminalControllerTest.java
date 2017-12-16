package ua.artcode.market.interfaces;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ua.artcode.market.controllers.TerminalControllerFactory;
import ua.artcode.market.models.Bill;
import ua.artcode.market.models.BillComparator;
import ua.artcode.market.models.Product;
import ua.artcode.market.models.employee.Employee;
import ua.artcode.market.models.employee.Salesman;
import ua.artcode.market.models.money.Money;
import ua.artcode.market.utils.Generator;

import java.util.List;
import static org.junit.Assert.*;
import static ua.artcode.market.models.BillComparator.other;

public class ITerminalControllerTest {

    private ITerminalController terminalController;

    @Before
    public void setUp() throws Exception {
        this.terminalController = TerminalControllerFactory.create();
    }

    @After
    public void tearDown() throws Exception {
        this.terminalController = null;
    }

    @Test
    public void createBill() throws Exception {
        Employee employee = terminalController.
                createSalesman("asd","asd","asd", new Money(123,3));
        Bill expected = terminalController.createBill(employee);
        assertEquals(0, expected.getProductsMap().size());
        assertNotNull(expected);
        assertEquals(1, expected.getId());
    }

    @Test
    public void addProduct() throws Throwable {
        Product product = Generator.createProduct();
        product.setId(1);
        Employee employee = terminalController.
                createSalesman("asd","asd","asd", new Money(123,3));
        terminalController.getIAppDb().saveProduct(product);
        Bill bill = terminalController.createBill(employee);
        bill = terminalController.addProduct(bill.getId(),
                terminalController.getIAppDb().
                        findProductById(product.getId()));

        terminalController.getIAppDb().saveBill(bill);
            assertEquals(1, bill.getProductsMap().size());
    }

    @Test
    public void getAllBills() throws Exception {
        Employee employee = terminalController.
                createSalesman("asd","asd","asd", new Money(123,3));
        terminalController.createBill(employee);
        terminalController.createBill(employee);
        terminalController.createBill(employee);
        terminalController.createBill(employee);
        terminalController.createBill(employee);
        assertEquals(5, terminalController.getBills().size());
    }

    @Test
    public void closeBill() throws Exception {
        Employee employee = terminalController.
                createSalesman("asd","asd","asd", new Money(123,3));
        Bill open = terminalController.createBill(employee);
        Bill close = terminalController.closeBill(open.getId());
        assertEquals(open, close);
        assertNotNull(close.getCloseTime());
    }

    @Test
    public void calculateAmountPriceNeg() throws Exception {
        Money amPrice = terminalController.calculateAmountPrice(null);
        assertTrue(new Money(0,0).equals(amPrice));
    }
    @Test
    public void calculateAmountPrice() throws Exception {
        Employee employee = null;
        try {
            employee = terminalController.createSalesman("ghjj","jhk","ghk", new Money(45,4));
        } catch (Exception e) {
            employee = new Salesman("ghjj","jhk","ghk", new Money(45,4));
        }
        Bill open = terminalController.createBill(employee);
        Product product = Generator.createProduct();
        open.toString();
        open.getOpenTime();
        open.getAmountPrice();
        open.setAmountPrice(new Money(0,0));
        terminalController.getIAppDb().getProducts().put(product, 15);
        open = terminalController.addProduct(open.getId(), product);
        Money amountPrice = terminalController.calculateAmountPrice(open);
        product.getPrice();
        product.getId();
        product.getName();

        assertNotEquals(0.0, amountPrice);
        assertNotEquals(0.0, open.getAmountPrice());
    }

    @Test
    public void createSalesman() throws Exception {
        Employee salesman = null;
        try {
            salesman = terminalController.
                    createSalesman("asdf", "1sdfg", "sdfsas", new Money(0, 0));
            System.out.println(salesman);
            salesman.getLogin();
            salesman.getFullName();
            salesman.getPassword();
            salesman.toString();
            Employee salesman2 = terminalController.
                    createSalesman("2", "2", "2", new Money(10, 0));
            salesman2.setLogin("2");
            salesman2.setPassword("2");
            salesman2.setFullName("2");

            assertFalse(salesman.equals(salesman2));
            assertNotEquals(null, salesman);
        } catch (Exception e) {
            assertNull(salesman);
        }

    }

//    @Test
//    public void login() throws Exception {
//        Employee salesman = terminalController.createSalesman("1", "1", "1");
//        salesman = terminalController.login("1", "1");
//        assertNotEquals(null, salesman);
//    }


    @Test
    public void saveAndRemoveBill() throws Exception {
        Employee salesman = null;
        try {
            salesman = terminalController.
                    createSalesman("1", "1", "1", new Money(0, 0));
        } catch (Exception e) {
            salesman = new Salesman("1", "1", "1", new Money(0, 0));
        }
        salesman.getLogin();
        Bill expected = terminalController.createBill(salesman);
        Bill expectedReturn = terminalController.getIAppDb().saveBill(expected);
        Bill acttual = terminalController.getIAppDb().
                removeBill(expectedReturn.getId());
        terminalController.prinBill(expected);
        assertEquals(expectedReturn,acttual);
    }

    @Test
    public void filter() throws Exception {
        Product product1 = new Product("asd", new Money(10, 0));
        Product product2 = new Product("asd1", new Money(20, 0));
        Product product3 = new Product("asd2", new Money(3, 0));

        product1.setName("1");
        product1.setId(1);
        product2.setName("2");
        product2.setId(2);
        product3.setName("3");
        product3.setId(3);

        product1.setPrice(new Money(1, 0));
        product2.setPrice(new Money(123, 0));
        product3.setPrice(new Money(12313, 42));

        Employee salesman1 = null;
        Employee salesman2 = null;
        Employee salesman3 = null;
        try {
            salesman1 = terminalController.
                    createSalesman("1123", "123", "1", new Money(0, 0));
            salesman2 = terminalController.
                    createSalesman("2", "12", "1", new Money(0, 0));
            salesman3 = terminalController.
                    createSalesman("2", "13", "1", new Money(0, 0));
        } catch (Exception e) {
            salesman1 = new Salesman("d","we","re",new Money(1,1));
            salesman2 = new Salesman("ad","wse","rse",new Money(1,1));
            salesman3 = new Salesman("ad","wsd","res",new Money(1,1));
        }
        terminalController.getIAppDb().getProducts().put(product1, 15);
        terminalController.getIAppDb().getProducts().put(product2, 15);
        terminalController.getIAppDb().getProducts().put(product3, 15);



        Bill bill1 = terminalController.createBill(salesman1);
        terminalController.addProduct(bill1.getId(), product1);
        terminalController.addProduct(bill1.getId(), product1);
        terminalController.addProduct(bill1.getId(), product1);

        Bill bill2 = terminalController.createBill(salesman2);
        terminalController.addProduct(bill2.getId(), product1);
        terminalController.addProduct(bill2.getId(), product2);
        terminalController.addProduct(bill2.getId(), product2);

        Bill bill3 = terminalController.createBill(salesman3);
        terminalController.addProduct(bill3.getId(), product3);
        terminalController.addProduct(bill3.getId(), product2);
        terminalController.addProduct(bill3.getId(), product2);

        Bill bill4 = terminalController.createBill(salesman3);
        terminalController.addProduct(bill4.getId(), product3);
        terminalController.addProduct(bill4.getId(), product3);
        terminalController.addProduct(bill4.getId(), product1);

        bill3.setEmployee(salesman3);
        bill2.setEmployee(salesman2);
        bill1.setEmployee(salesman1);
        bill4.setEmployee(salesman1);



        terminalController.closeBill(bill1.getId());
        terminalController.closeBill(bill2.getId());
        terminalController.closeBill(bill3.getId());
        terminalController.closeBill(bill4.getId());

//        terminalController.logout((Salesman) salesman1);
        terminalController.prinBill(bill1);
        terminalController.getIAppDb();
        terminalController.getBills();
        terminalController.calculateAmountPrice(bill1);


        List<Bill> sorted = terminalController.getIAppDb().filter(salesman1,
                product1, null, null, BillComparator.
                        billComparator.thenComparing(other));

        terminalController.getIAppDb().removeBill(1);
        terminalController.getIAppDb().averageAmountPrice((Salesman) salesman1,null, null);
        terminalController.getIAppDb().minAmountPrice((Salesman) salesman1,null, null);
        terminalController.getIAppDb().maxAmountPrice((Salesman) salesman1,null, null);

       /* LocalDateTime.MIN, LocalDateTime.MAX*/
//        assertTrue(sorted.get(0).getAmountPrice() >=
//                sorted.get(1).getAmountPrice());
        assertTrue(sorted.size() == 2);
    }
}