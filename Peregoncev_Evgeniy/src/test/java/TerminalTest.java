import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


/**
 * Created by ENIAC on 11.11.2017.
 */
public class TerminalTest {

    Salesman[] testSales;

    Salesman testSalesman1;
    Salesman testSalesman2;
    Salesman testSalesman3;
    Salesman testSalesman4;


    @Before
    public void setUp() {
        testSales = new Salesman[10];

        testSalesman1 = new Salesman("Inna", "InnaLog", "InnaPass");
        testSalesman2 = new Salesman("Vanessa", "VanessaLog", "VanessaPass");
        testSalesman3 = new Salesman("Izolda", "IzoldaLog", "IzoldaPass");
        testSalesman4 = new Salesman("Dasha", "DashaLog", "DashaPass");
    }

    @After
    public void setDown() {
        testSales = null;

        testSalesman1 = null;
        testSalesman2 = null;
        testSalesman3 = null;
        testSalesman4 = null;

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

    }

    @Test
    public Salesman findSalesmanByLogin(Salesman[] testSales, Salesman testSalesman2) throws Exception {

        this.testSales[0] = testSalesman1;
        this.testSales[1] = testSalesman2;
        this.testSales[2] = testSalesman3;
        this.testSales[3] = testSalesman4;

Salesman searhingSeller = findSalesmanByLogin(testSales,testSalesman3);
        return searhingSeller;

//        Assert.assertSame();
    }




}
