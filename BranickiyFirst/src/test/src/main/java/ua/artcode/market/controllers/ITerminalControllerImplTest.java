package src.main.java.ua.artcode.market.controllers;

import org.junit.Before;
import org.junit.Test;
import src.main.java.ua.artcode.market.models.Bill;
import src.main.java.ua.artcode.market.models.Salesman;
import src.main.java.ua.artcode.market.models.Statistics;
import src.main.java.ua.artcode.market.proxy.TerminalControllerProxy;

import javax.smartcardio.TerminalFactory;

import static org.junit.Assert.*;

public class ITerminalControllerImplTest {

    private static TerminalControllerProxy terminal;

    private static String name1 = "Dima ";
    private static String name2 = "Ivan ";
    private static String name3 = "Kolia ";

    private static String login1 = "lDima";
    private static String login2 = "lIvan";
    private static String login3 = "lKolia";

    private static String password1 = "123qwe";
    private static String password2 = "456qwe";
    private static String password3 = "789qwe";


    @Before
    public void before() {

        terminal = TerminalFactory.create();

        terminal.getIAppDB().addProductToDataBase("Milk", 100);
        terminal.getIAppDB().addProductToDataBase("Apple", 70);
        terminal.getIAppDB().addProductToDataBase("Meat", 10);

        terminal.addSalesman(name1, login1, password1);
        terminal.addSalesman(name2, login2, password2);
        terminal.addSalesman(name3, login3, password3);

        terminal.signIn(login3, password3);
        terminal.createBill();
        terminal.addProductToBill(1);
        terminal.addProductToBill(2);
        terminal.addProductToBill(3);
        terminal.closeAndSaveBill();
        terminal.logOut();

        terminal.signIn(name1, password1);
        terminal.createBill();
        terminal.addProductToBill(1);
        terminal.addProductToBill(2);
        terminal.closeAndSaveBill();
        terminal.logOut();

        terminal.signIn(login2, password2);
        terminal.createBill();
        terminal.addProductToBill(1);
        terminal.closeAndSaveBill();
        terminal.logOut();

        terminal.getIAppDB().findSalesmanByLoginOrName(login1);
    }


    @Test
    public void addSalesman() throws Exception {
        // test count of salesmans
        assertEquals(3, terminal.getCountOfSalesman());
    }

    @Test
    public void signIn() throws Exception {
        // if salesman was sign in
        terminal.signIn(login2, password2);
        assertTrue(terminal.getIsLogged());
    }

    @Test
    public void findBillById() throws Exception {

        Bill exceptedBill = terminal.getIAppDB().findBillById(7);

        assertEquals(exceptedBill, terminal.getAllBills().get(0));
    }

    @Test
    public void findSalesman() throws Exception {

        // salesman search
        Salesman findSalesman = terminal.getIAppDB().findSalesmanByLoginOrName(login2);

        assertEquals(findSalesman.getFullName(), name2);
        assertEquals(findSalesman.getLogin(), login2);
        assertEquals(findSalesman.getPass(), password2);
    }

    @Test
    public void logOut() throws Exception {

    }

    @Test
    public void createBill() throws Exception {
    }

    @Test
    public void closeAndSaveBill() throws Exception {
        terminal.signIn(name1, password1);
        terminal.createBill();
        terminal.addProductToBill(1);
        terminal.closeAndSaveBill();
        assertEquals("10:20:30", terminal.getAllBills().get(3).getCloseTime().toString());
    }

    @Test
    public void addProductToBill() throws Exception {
    }

    @Test
    public void makeStatistics() throws Exception {

        Statistics statistics = terminal.makeStatistics();
        Statistics expStat = new Statistics(180,
                terminal.getIAppDBImp().findSalesmanByLoginOrName(login3),
                100,
                terminal.getIAppDBImp().findSalesmanByLoginOrName(login2),
                150,
                450);
        assertEquals(statistics, expStat);
    }



}