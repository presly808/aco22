import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
/**
 * Created by ENIAC on 11.11.2017.
 */
public class TerminalTest {

    Terminal testTerminal;

    Salesman testSalesman1;
    Salesman testSalesman2;
    Salesman testSalesman3;
    Salesman testSalesman4;

    Bill testBill1;
    Bill testBill2;
    Bill testBill3;
    Bill testBill4;


    @Before
    public void setUp() {
        testTerminal = new Terminal();

        testSalesman1 = new Salesman("Inna", "InnaLog", "InnaPass");
        testSalesman2 = new Salesman("Vanessa", "VanessaLog", "VanessaPass");
        testSalesman3 = new Salesman("Izolda", "IzoldaLog", "IzoldaPass");
        testSalesman4 = new Salesman("Dasha", "DashaLog", "DashaPass");

        testBill1 = new Bill(0, testSalesman1, "close time", 001);
        testBill2 = new Bill(0, testSalesman1, "close time", 002);
        testBill3 = new Bill(0, testSalesman1, "close time", 003);
        testBill4 = new Bill(0, testSalesman1, "close time", 004);


    }

    @After
    public void setDown() {

        testSalesman1 = null;
        testSalesman2 = null;
        testSalesman3 = null;
        testSalesman4 = null;

        testBill1 = null;
        testBill2 = null;
        testBill3 = null;
        testBill4 = null;

    }

    
    @Test
    public void login() throws Exception {

    }

    @Test
    public void createbill() throws Exception {

    }

    @Test
    public void closeAndSaveBill() throws Exception {

    }

    @Test
    public void findBillById() throws Exception {
        testTerminal.getBils()[0] = testBill1;
        testTerminal.getBils()[1] = testBill2;
        testTerminal.getBils()[2] = testBill3;
        testTerminal.getBils()[3] = testBill4;

        testTerminal.setBillCountSize(4);
        Assert.assertSame(testTerminal.findBillById(testTerminal.getBils(),003),testBill3);

    }

    @Test
    public void findSalesmanByLogin() throws Exception {

        testTerminal.getSales()[0] = testSalesman1;
        testTerminal.getSales()[1] = testSalesman2;
        testTerminal.getSales()[2] = testSalesman3;
        testTerminal.getSales()[3] = testSalesman4;

        testTerminal.setSalesCountSize(4);

        Assert.assertSame(testTerminal.findSalesmanByLogin(testTerminal.getSales(), "VanessaLog"), testSalesman2);
    }


}
