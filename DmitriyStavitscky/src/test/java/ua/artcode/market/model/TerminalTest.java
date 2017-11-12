package ua.artcode.market.model;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;
import static ua.artcode.market.model.Terminal.MAX_COUNT_OF_SALESMANS;

public class TerminalTest {

    private static Terminal terminal;

    private static String name1 = "Dima Stavitscky";
    private static String name2 = "Ivan Raskolnikov";
    private static String name3 = "Kolia Ivanov";

    private static String login1 = "lDima";
    private static String login2 = "lIvan";
    private static String login3 = "lKolia";

    private static int pass1 = 123;
    private static int pass2 = 456;
    private static int pass3 = 789;

    private static Salesman[] salesmans = new Salesman[MAX_COUNT_OF_SALESMANS];

    @BeforeClass
    public static void beforeClass() {

        terminal = new Terminal();

        terminal.addSalesman(name1, login1, pass1);
        terminal.addSalesman(name2, login2, pass2);
        terminal.addSalesman(name3, login3, pass3);

        salesmans = terminal.getSalesmans();
        Bill[] bills = terminal.getBills();
    }

    @Test
    public void addSalesman() throws Exception {

        // test count of salesmans
        assertEquals(3, terminal.getCountOfSalesman());
    }

    @Test
    public void signIn() throws Exception {

        // if salesman was sign in
        terminal.signIn(true, login2, pass2);
        assertTrue(terminal.getIsLogged());
    }

    @Test
    public void closeAndSaveBillAndConvertCloseTime() {
        terminal.signIn(false, name1, pass1);
        terminal.createBill(5);
        terminal.addProduct("Fanta", 15, 9.50);
        terminal.closeAndSaveBill(10, 20, 30);
        assertEquals("10:20:30", terminal.getBills()[0].getTime().convTime());
    }

    @Test
    public void findBillById() throws Exception {

        // bill search
        terminal.setLogged(true);
        terminal.createBill(1);
        terminal.createBill(2);
        terminal.createBill(3);

        Bill[] bills = terminal.getBills();
        Bill excpectedBill = terminal.findBillById(2);

        assertEquals(excpectedBill, bills[2]);
    }

    @Test
    public void findSalesman() throws Exception {

        // salerman search
        Salesman findSalesman = terminal.findSalesman(login2, true);

        assertEquals(findSalesman.getFullName(), name2);
        assertEquals(findSalesman.getLogin(), login2);
        assertEquals(findSalesman.getPass(), pass2);
    }

    @Test
    public void getTopNofSalesMan() throws Exception {

        // search top salesman
        salesmans[0].setSumOfAllSales(150);
        salesmans[1].setSumOfAllSales(200);
        salesmans[2].setSumOfAllSales(100);

        Salesman topSalesman = terminal.getTopNofSalesMan();

        assertEquals(topSalesman.getSumOfAllSales(), salesmans[1].getSumOfAllSales(), 1);
    }

}