package ua.artcode.market.model;

import org.junit.BeforeClass;
import org.junit.Test;
import ua.artcode.market.controller.Terminal;

import java.util.Arrays;

import static org.junit.Assert.*;
import static ua.artcode.market.controller.Terminal.MAX_COUNT_OF_SALESMANS;

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

        terminal.signIn(true, login3, pass3 );
        terminal.createBill(1);
        terminal.addProduct("Milk", 19, 100);
        terminal.closeAndSaveBill(23, 58, 59);
        terminal.logOut();

        terminal.signIn(false, name1, pass1);
        terminal.createBill(2);
        terminal.addProduct("Fish", 8, 50);
        terminal.closeAndSaveBill(13, 13, 13);
        terminal.logOut();

        terminal.signIn(true, login2, pass2);
        terminal.createBill(3);
        terminal.addProduct("Apple", 7, 10);
        terminal.closeAndSaveBill(1, 1,1);
        terminal.logOut();
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
    public void closeAndSaveBill() {

        terminal.signIn(false, name1, pass1);
        terminal.createBill(5);
        terminal.addProduct("Fanta", 15, 9.50);
        terminal.closeAndSaveBill(10, 20, 30);
        assertEquals("10:20:30", terminal.getBills()[terminal.getCountOfBills() - 1].getTime().toString());
    }

    @Test
    public void findBillById() throws Exception {

        Bill[] bills = terminal.getBills();
        Bill excpectedBill = terminal.findBillById(2);

        assertEquals(excpectedBill, bills[1]);
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

    @Test
    public void filterAndSortWitIdCompar() throws Exception {

        Bill[] billsFilter = terminal.filter(terminal.getBills(),
                new Time(0, 0,0),
                new Time(23,59,59),
                new BillIdComparator());

        assertEquals(3, terminal.getCountOfBills());
    }

    @Test
    public void sortByAmountPrice() throws Exception {

        Bill[] bills = Arrays.copyOf(terminal.getBills(), terminal.getCountOfBills());
        Arrays.sort(bills, new BillAmountPriceComparator());

        assertEquals(10, bills[0].getAmountPrice(), 1);
        assertEquals(50, bills[1].getAmountPrice(), 1);
        assertEquals(100, bills[2].getAmountPrice(), 1);
    }

    @Test
    public void sortByProductsCount() throws Exception {

        Bill[] bills = Arrays.copyOf(terminal.getBills(), terminal.getCountOfBills());
        bills[0].setProductsCount(5);
        bills[1].setProductsCount(3);

        Arrays.sort(bills, new BillProductsCountComparator());

        assertEquals(1, bills[0].getProductsCount());
        assertEquals(3, bills[1].getProductsCount());
        assertEquals(5, bills[2].getProductsCount());
    }

    @Test
    public void sortBySalesman() throws Exception {

        Bill[] bills = Arrays.copyOf(terminal.getBills(), terminal.getCountOfBills());
        Arrays.sort(bills, new BillSalesmanComparator());

        assertEquals(name1, bills[0].getSalesman().getFullName());
        assertEquals(name2, bills[1].getSalesman().getFullName());
        assertEquals(name3, bills[2].getSalesman().getFullName());
    }

    @Test
    public void sortByTime() throws Exception {

        Bill[] bills = Arrays.copyOf(terminal.getBills(), terminal.getCountOfBills());
        Arrays.sort(bills, new BillTimeComparator());

        assertTrue(new Time(1,1,1).
                        compareTo(bills[0].getTime()) == 0);

        assertTrue(new Time(13,13,13).
                        compareTo(bills[1].getTime()) == 0);

        assertTrue(new Time(23,58,59).
                        compareTo(bills[2].getTime()) == 0);
    }
}