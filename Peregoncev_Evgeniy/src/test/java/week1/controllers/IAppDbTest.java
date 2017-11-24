package week1.controllers;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import week1.interfaces.IAppDb;
import week1.model.Bill;

import static org.junit.Assert.*;

/**
 * Created by ENIAC on 20.11.2017.
 */
public class IAppDbTest {

    private IAppDb appDb;

    @Before
    public void setUp() {
        appDb = new IAppDbImpl();
    }

    @After
    public void tearDown(){
        appDb = null;
    }

    @Test
    public void getAllBills() throws Exception {
        appDb.getAllBills();
        assertEquals(0, appDb.getAllBills().size());
    }

    @Test
    public void getAllSalesMans() throws Exception {
        appDb.getAllSalesMans();
        assertEquals(3, appDb.getAllSalesMans().size());
    }

    @Test
    public void findByBillId() throws Exception {
        Bill saved = appDb.saveBill(new Bill());
        appDb.findByBillId(saved.getId());
        assertSame(appDb.findByBillId(saved.getId()),saved);
    }

    @Test
    public void saveBill() throws Exception {
        Bill saved = appDb.saveBill(new Bill());
        assertEquals(0, saved.getId());
    }

    @Test
    public void removeBill() throws Exception {


    }

    @Test
    public void update() throws Exception {

    }
}