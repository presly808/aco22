package ua.artcode.market.controller;

import org.junit.Before;
import org.junit.Test;
import ua.artcode.market.models.Product;

import static org.junit.Assert.*;

/**
 * Created by serhii on 19.11.17.
 */
public class IAppDbTest {

    private IAppDb appDb;

    @Before
    public void setUp(){
        appDb = new IAppDbImpl();
    }

    @Test
    public void getAllBills() throws Exception {
    }

    @Test
    public void getAllProducts() throws Exception {
        appDb.getAllProducts();
        assertEquals(0, appDb.getAllProducts().size());
    }

    @Test
    public void findByBillId() throws Exception {

    }

    @Test
    public void findByProductId() throws Exception {

        Product saved = appDb.saveProduct(new Product());
        appDb.findByProductId(saved.getId());
    }

    @Test
    public void saveBill() throws Exception {
    }

    @Test
    public void saveProduct() throws Exception {
        Product saved = appDb.saveProduct(new Product());
        //assertNotEquals(0, saved.getId());
    }

    @Test
    public void removeBill() throws Exception {


    }

    @Test
    public void removeProduct() throws Exception {
        Product saved = appDb.saveProduct(new Product());
        Product removed = appDb.removeProduct(saved.getId());
        //assertEquals(saved.getId(),removed.getId());
    }

    @Test
    public void update() throws Exception {
    }

}