package week1.controller;

import org.junit.Before;
import org.junit.Test;
import week1.controller.IAppDbImpl;
import week1.controller.ITerminalController;
import week1.controller.ITerminalControllerControllerImpl;
import week1.model.Bill;
import week1.model.Product;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

/**
 * Created by ENIAC on 11.11.2017.
 */
public class ITerminalControllerControllerImplTest {
    private ITerminalController terminalController;

    @Before
    public void setUp(){
        this.terminalController = new ITerminalControllerControllerImpl(new IAppDbImpl());
    }

    @Test
    public void createBill() throws Exception {

        Bill bill = terminalController.createBill();
        assertEquals(0, bill.getProductList().size());
        assertEquals(0, bill.getId());

    }

    @Test
    public void addProduct() throws Exception {

        Bill bill = terminalController.createBill();

        Product product = new Product();
        product.setPrice(123);
        product.setId(1);
        product.setName("Potato");

        terminalController.addProduct(bill.getId(), product);

        product.getId();
        product.getName();
        product.getPrice();


        assertEquals(1, bill.getProductList().size());

    }

    @Test
    public void getAllBills() throws Exception {

        Bill bill1 = terminalController.createBill();
        Bill bill2 = terminalController.createBill();
        Bill bill3 = terminalController.createBill();
        Bill bill4 = terminalController.createBill();

        assertEquals(4, terminalController.getAllBills().size());
    }

    @Test
    public void closeBill() throws Exception {

        Bill bill = terminalController.createBill();
        Bill closed = terminalController.closeBill(bill.getId());

        assertTrue(closed.isClosed());

    }







//    @Test
//    public void login() throws Exception {
//
//        testITerminalControllerImpl.getSales()[0] = testSalesman1;
//        testITerminalControllerImpl.getSales()[1] = testSalesman2;
//        testITerminalControllerImpl.getSales()[2] = testSalesman3;
//        testITerminalControllerImpl.getSales()[3] = testSalesman4;
//
//        testITerminalControllerImpl.setSalesCountSize(4);
//
//        testITerminalControllerImpl.login("IzoldaLog", "IzoldaPass");
//
//        assertEquals(2, testITerminalControllerImpl.getCurrentSallerIndex());
//
//    }
//
//    @Test
//    public void createBill() throws Exception {
//
//        testITerminalControllerImpl.setCurrentSallerIndex(1);
//
//        testITerminalControllerImpl.createBill(testBill1);
//        testITerminalControllerImpl.createBill(testBill2);
//        testITerminalControllerImpl.createBill(testBill3);
//        testITerminalControllerImpl.createBill(testBill4);
//
//        assertEquals(3, testITerminalControllerImpl.getBils()[2].getId());
//
//    }
//
//    @Test
//    public void findBillById() throws Exception {
//
//        testITerminalControllerImpl.getBils()[0] = testBill1;
//        testITerminalControllerImpl.getBils()[1] = testBill2;
//        testITerminalControllerImpl.getBils()[2] = testBill3;
//        testITerminalControllerImpl.getBils()[3] = testBill4;
//
//        testITerminalControllerImpl.setBillCountSize(4);
//
//        assertSame(testITerminalControllerImpl.findBillById(testITerminalControllerImpl.getBils(), 3), testBill3);
//
//    }
//
//    @Test
//    public void closeAndSaveBill() throws Exception {
//
//        testITerminalControllerImpl.getBils()[0] = testBill1;
//
//        testITerminalControllerImpl.closeAndSaveBill(testBill1);
//
//        assertTrue((testBill1.getIsclosed()) && (testBill1.getTime().printTime().equals(testBill1.getTime().getCloseTime())));
//
//    }
//
//    @Test
//    public void findSalesmanByLogin() throws Exception {
//
//        testITerminalControllerImpl.getSales()[0] = testSalesman1;
//        testITerminalControllerImpl.getSales()[1] = testSalesman2;
//        testITerminalControllerImpl.getSales()[2] = testSalesman3;
//        testITerminalControllerImpl.getSales()[3] = testSalesman4;
//
//        testITerminalControllerImpl.setSalesCountSize(4);
//
//        assertSame(testITerminalControllerImpl.findSalesmanByLogin(testITerminalControllerImpl.getSales(), "VanessaLog"), testSalesman2);
//
//    }

}
