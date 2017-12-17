package ua.artcode.market.models;

import org.junit.Before;
import org.junit.Test;
import ua.artcode.market.exceptions.AppDBException;
import ua.artcode.market.exceptions.SaveBillException;
import ua.artcode.market.exceptions.TerminalException;
import ua.artcode.market.exceptions.WrongSubordinateException;
import ua.artcode.market.factory.TerminalFactory;
import ua.artcode.market.proxy.TerminalControllerProxyHistory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TerminalControllerTest {

    private static TerminalControllerProxyHistory terminal;

    private static String name1 = "Dima Stavitscky";
    private static String name2 = "Ivan Raskolnikov";
    private static String name3 = "Kolia Ivanov";

    private static String login1 = "lDima";
    private static String login2 = "lIvan";
    private static String login3 = "lKolia";

    private static int pass1 = 123;
    private static int pass2 = 456;
    private static int pass3 = 789;

    @Before
    public void before() throws Exception {

        terminal = TerminalFactory.create();

        terminal.getAppDB().addProductToDataBase("Milk", 100);
        terminal.getAppDB().addProductToDataBase("Apple", 70);
        terminal.getAppDB().addProductToDataBase("Meat", 10);

        terminal.addSalesman(name1, login1, pass1);
        terminal.addSalesman(name2, login2, pass2);
        terminal.addSalesman(name3, login3, pass3);

        terminal.signIn(login3, pass3);
        terminal.createBill();
        terminal.addProductToBill(1);
        terminal.addProductToBill(2);
        terminal.addProductToBill(3);
        terminal.closeAndSaveBill();
        terminal.logOut();

        terminal.signIn(name1, pass1);
        terminal.createBill();
        terminal.addProductToBill(1);
        terminal.addProductToBill(2);
        terminal.closeAndSaveBill();
        terminal.logOut();

        terminal.signIn(login2, pass2);
        terminal.createBill();
        terminal.addProductToBill(1);
        terminal.closeAndSaveBill();
        terminal.logOut();

        terminal.getAppDB().findSalesmanByLoginOrName(login1).setDirector(true);
    }

    @Test
    public void addSalesman() throws Exception {

        // test count of salesmans
        assertEquals(3, terminal.getCountOfSalesman());
    }

    @Test
    public void signIn() throws Exception {

        // if salesman was sign in
        terminal.signIn(login2, pass2);
        assertTrue(terminal.getIsLogged());
    }

    @Test
    public void findBillById() throws Exception {

        Bill excpectedBill = terminal.getAppDB().findBillById(7);

        assertEquals(excpectedBill, terminal.getAllBills().get(0));
    }

    @Test
    public void findSalesman() throws Exception {

        // salerman search
        Salesman findSalesman = terminal.getAppDB().findSalesmanByLoginOrName(login2);

        assertEquals(findSalesman.getFullName(), name2);
        assertEquals(findSalesman.getLogin(), login2);
        assertEquals(findSalesman.getPass(), pass2);
    }

    @Test
    public void getTopNofSalesMan() throws Exception {

        // search top salesman
        terminal.getAppDB().getSalesmans().get(0).setSumOfAllSales(150);
        terminal.getAppDB().getSalesmans().get(1).setSumOfAllSales(200);
        terminal.getAppDB().getSalesmans().get(2).setSumOfAllSales(100);

        Salesman topSalesman = terminal.getTopNofSalesMan();

        assertEquals(topSalesman.getSumOfAllSales(),
                terminal.getAppDB().getSalesmans().get(1).getSumOfAllSales(), 1);
    }

    @Test
    public void sortByAmountPrice() throws Exception {

        terminal.getAllBills().sort(new Bill.AmountPriceComparator());

        assertEquals(100, terminal.getAllBills().get(0).getAmountPrice(), 1);
        assertEquals(170, terminal.getAllBills().get(1).getAmountPrice(), 1);
        assertEquals(180, terminal.getAllBills().get(2).getAmountPrice(), 1);
    }

    @Test
    public void makeStatistics() throws Exception {

        Statistics statistics = terminal.makeStatistics();
        Statistics expStat = new Statistics(180,
                terminal.getAppDB().findSalesmanByLoginOrName(login3),
                100,
                terminal.getAppDB().findSalesmanByLoginOrName(login2),
                150,
                450);
        assertEquals(statistics, expStat);
    }

    @Test
    public void sortByProductsCount() throws Exception {

        terminal.getAllBills().sort(new Bill.ProductsCountComparator());

        assertEquals(1, terminal.getAllBills().get(0).getProducts().size());
        assertEquals(2, terminal.getAllBills().get(1).getProducts().size());
        assertEquals(3, terminal.getAllBills().get(2).getProducts().size());
    }

    @Test
    public void sortBySalesman() throws Exception {

        terminal.getAllBills().sort(new Bill.SalesmanComparator());

        assertEquals(name1, terminal.getAllBills().get(0).getSalesman().getFullName());
        assertEquals(name2, terminal.getAllBills().get(1).getSalesman().getFullName());
        assertEquals(name3, terminal.getAllBills().get(2).getSalesman().getFullName());
    }

    @Test
    public void appDBUpdate() throws Exception {

        Bill newBill = new Bill(terminal.getAppDB().findSalesmanById(5), 8);
        terminal.getAppDB().update(newBill);

        assertEquals(newBill, terminal.getAllBills().get(1));
    }

    @Test
    public void testCalculateSalary() throws Exception {

        terminal.signIn(login1, pass1);
        Salesman sub1 = new Salesman("Diman", "lside", 107, 101);
        Salesman sub2 = new Salesman("Dima", "lsid", 102, 102);
        Salesman sub3 = new Salesman("Dim", "lsi", 103, 103);
        Salesman sub4 = new Salesman("Di", "ls", 104, 104);
        Salesman sub5 = new Salesman("D", "l", 105, 105);

        sub1.setSumOfAllSales(100_000);
        sub2.setSumOfAllSales(150_000);
        sub3.setSumOfAllSales(200_000);
        sub4.setSumOfAllSales(250_000);
        sub5.setSumOfAllSales(300_000);

        Salesman dir1 = terminal.getAppDB().findSalesmanByLoginOrName(login1);
        Salesman dir2 = terminal.getAppDB().findSalesmanByLoginOrName(login2);
        Salesman dir3 = terminal.getAppDB().findSalesmanByLoginOrName(login3);

        dir1.setSumOfAllSales(50_000);
        dir2.setSumOfAllSales(70_000);
        dir3.setSumOfAllSales(90_000);

        terminal.addSubSalesman(dir1, sub1);
        terminal.addSubSalesman(dir1, dir2);
        terminal.addSubSalesman(dir2, sub2);
        terminal.addSubSalesman(dir2, sub3);
        terminal.addSubSalesman(dir2, sub4);
        terminal.addSubSalesman(dir2, dir3);
        terminal.addSubSalesman(dir3, sub5);

        terminal.calculateSalesmanSalary(dir1);
        assertEquals(61679, (int) terminal.requiredAmountFromTheDepartment(dir1));
    }

    @Test(expected = WrongSubordinateException.class)
    public void testCheckIfIsBoss() throws Exception {

        terminal.signIn(login1, pass1);

        Salesman a1 = terminal.addSalesman("Dak", "dak", 11);
        Salesman a2 = terminal.addSalesman("Kok", "kok", 11);
        Salesman a3 = terminal.addSalesman("Olia", "Ola", 11);
        Salesman a4 = terminal.addSalesman("Oli", "Ol", 11);
        Salesman a5 = terminal.addSalesman("Ol", "O", 11);
        Salesman a6 = terminal.addSalesman("Avan", "ava", 11);
        Salesman a7 = terminal.addSalesman("Nelia", "nel", 11);
        Salesman a8 = terminal.addSalesman("Petrik", "pet", 11);

        terminal.addSubSalesman(terminal.getLoggedSalesman(), a1);
        terminal.addSubSalesman(a1, a2);
        terminal.addSubSalesman(a2, a3);
        terminal.addSubSalesman(a2, a4);
        terminal.addSubSalesman(a3, a6);
        terminal.addSubSalesman(a4, a5);
        terminal.addSubSalesman(a6, a7);
        terminal.addSubSalesman(a7, a8);
        terminal.addSubSalesman(a7, a2);
    }

    @Test(expected = SaveBillException.class)
    public void testSaveBillException() throws Exception {

        terminal.signIn(login1, pass1);
        terminal.createBill();
        terminal.closeAndSaveBill();
    }

    @Test(expected = AppDBException.class)
    public void testAppDBException() throws Exception {

        terminal.signIn(login1, pass1);
        terminal.createBill();
        terminal.addProductToBill(798);
    }

    @Test(expected = TerminalException.class)
    public void testTerminalException() throws Exception {

        terminal.createBill();
    }
}