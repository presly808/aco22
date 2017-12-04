package ua.artcode.market.interfaces;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ua.artcode.market.controllers.IAppDbImpl;
import ua.artcode.market.controllers.ITerminalControllerImpl;
import ua.artcode.market.exclude.exception.*;
import ua.artcode.market.models.Bill;

import static org.junit.Assert.*;

public class IAppDbTest {
    private IAppDb iAppDb;
    private ITerminalController iTerminalController;

    @Before
    public void setUp() throws Exception {
        iAppDb = new IAppDbImpl();
        iTerminalController = new ITerminalControllerImpl(iAppDb);
    }

    @After
    public void tearDown() throws Exception {
        iAppDb = null;
        iTerminalController = null;
    }

    @Test
    public void findBillById() throws Exception {
        Bill bill1 = iTerminalController.createBill();
        Bill bill2 = iTerminalController.createBill();
        Bill bill3 = iTerminalController.createBill();
        Bill bill4 = iTerminalController.createBill();
        System.out.println(bill1.toString() +  bill2.toString() +
                bill4.toString());
        Bill bill = iAppDb.findBillById(3);

        assertTrue(bill3.getId() == bill.getId());
    }

    @Test
    public void findBillByIdNeg() throws Exception {
        Bill bill = null;
        try {
            bill = iAppDb.findBillById(1);
        }catch (BillNotFoundException ignored) {
            System.out.println("Bill not found");
            ignored.printStackTrace();
        }
        assertNull(bill);
    }
}