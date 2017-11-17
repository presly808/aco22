package week1;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
/**
 * Created by ENIAC on 11.11.2017.
 */
public class TerminalTest {

    private Terminal testTerminal;

    private Salesman testSalesman1;
    private Salesman testSalesman2;
    private Salesman testSalesman3;
    private Salesman testSalesman4;

    private Bill testBill1;
    private Bill testBill2;
    private Bill testBill3;
    private Bill testBill4;

    private Time testTime;

    @Before
    public void setUp() {

        testTerminal = new Terminal();
        testTime = new Time();

        testSalesman1 = new Salesman("Inna", "InnaLog", "InnaPass");
        testSalesman2 = new Salesman("Vanessa", "VanessaLog", "VanessaPass");
        testSalesman3 = new Salesman("Izolda", "IzoldaLog", "IzoldaPass");
        testSalesman4 = new Salesman("Dasha", "DashaLog", "DashaPass");

        testBill1 = new Bill(0, testSalesman1, testTime, 1);
        testBill2 = new Bill(0, testSalesman1, testTime, 2);
        testBill3 = new Bill(0, testSalesman1, testTime, 3);
        testBill4 = new Bill(0, testSalesman1, testTime, 4);

    }

    @After
    public void setDown() {

        testTerminal = null;

        testSalesman1 = null;
        testSalesman2 = null;
        testSalesman3 = null;
        testSalesman4 = null;

        testBill1 = null;
        testBill2 = null;
        testBill3 = null;
        testBill4 = null;

        testTime = null;

    }


    @Test
    public void login() throws Exception {

        testTerminal.getSales()[0] = testSalesman1;
        testTerminal.getSales()[1] = testSalesman2;
        testTerminal.getSales()[2] = testSalesman3;
        testTerminal.getSales()[3] = testSalesman4;

        testTerminal.setSalesCountSize(4);

        testTerminal.login("IzoldaLog", "IzoldaPass");

        assertEquals(2, testTerminal.getCurrentSallerIndex());

    }

    @Test
    public void createBill() throws Exception {

        testTerminal.setCurrentSallerIndex(1);

        testTerminal.createbill(testBill1);
        testTerminal.createbill(testBill2);
        testTerminal.createbill(testBill3);
        testTerminal.createbill(testBill4);

        assertEquals(3, testTerminal.getBils()[2].getBillId());

    }

    @Test
    public void findBillById() throws Exception {

        testTerminal.getBils()[0] = testBill1;
        testTerminal.getBils()[1] = testBill2;
        testTerminal.getBils()[2] = testBill3;
        testTerminal.getBils()[3] = testBill4;

        testTerminal.setBillCountSize(4);

        assertSame(testTerminal.findBillById(testTerminal.getBils(), 3), testBill3);

    }

    @Test
    public void closeAndSaveBill() throws Exception {

        testTerminal.getBils()[0] = testBill1;

        testTerminal.closeAndSaveBill(testBill1);

        assertTrue((testBill1.getIsclosed()) && (testBill1.getTime().printTime().equals(testBill1.getTime().getCloseTime())));

    }

    @Test
    public void findSalesmanByLogin() throws Exception {

        testTerminal.getSales()[0] = testSalesman1;
        testTerminal.getSales()[1] = testSalesman2;
        testTerminal.getSales()[2] = testSalesman3;
        testTerminal.getSales()[3] = testSalesman4;

        testTerminal.setSalesCountSize(4);

        assertSame(testTerminal.findSalesmanByLogin(testTerminal.getSales(), "VanessaLog"), testSalesman2);

    }

}
