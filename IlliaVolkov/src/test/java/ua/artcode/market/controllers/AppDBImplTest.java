package ua.artcode.market.controllers;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ua.artcode.market.factory.MarketFactory;
import ua.artcode.market.model.Bill;
import ua.artcode.market.model.Product;
import ua.artcode.market.model.SalesMan;
import ua.artcode.market.model.Terminal;
import ua.artcode.market.proxy.MarketProxy;


import java.util.List;


public class AppDBImplTest {

    private AppDBImpl appDB;
    private Terminal terminal;

    @Before
    public void initTest() throws Exception {

        MarketProxy marketProxy = MarketFactory.createProxy();

        this.appDB = AppDBImpl.getEntity();

        this.appDB.generator.initSalesMans(2);
        this.appDB.generator.initProductsPrice(3);

        this.terminal = this.appDB.createTerminal();
        this.appDB.terminalController.setAutorizedSalesMan(this.terminal, this.appDB.getSalesManList().get(0));

        this.appDB.generator.initBill(this.terminal, this.appDB.getProductsPrice().size());

        String PriceOfProductsForPrint = this.appDB.statistics.getPriceOfProductsForPrint(this.appDB.getProductsPrice());
        String BillProductsForPrint = this.appDB.statistics.getBillProductsForPrint(this.appDB.getBillsTerminal(this.terminal).get(0));
        String BillHeadInfoForPrint = this.appDB.statistics.getBillHeadInfoForPrint(this.appDB.getBillsTerminal(this.terminal).get(0));
    }

    @After
    public void exitTest(){ this.appDB = null; }

    @Test
    public void remove() throws Exception {

        List<Bill> billList = this.appDB.getBillsTerminal(this.terminal);

        int sizeBillList = billList.size();

        this.appDB.remove(billList.get(0));

        Assert.assertEquals(sizeBillList, this.appDB.getBillsTerminal(this.terminal).size()+1);
    }

    @Test
    public void getAll() throws Exception {

        this.appDB.clearList(Product.class);
        this.appDB.generator.initProductsPrice(3);

        List<? extends Object> productpList = this.appDB.getAll(Product.class);

        int sizeProductpList = productpList.size();

        Assert.assertEquals(sizeProductpList, 3);
    }

    @Test
    public void findSalesMan() throws Exception {
    }

    @Test
    public void findProductByCode() throws Exception {
    }

    @Test
    public void createSalesMan() throws Exception {

        SalesMan salesMan = this.terminal.getSalesMan();

        Assert.assertEquals("SalesMan1", salesMan.getFullName());
        Assert.assertEquals( "SM1", salesMan.getLogin());
        Assert.assertEquals( "1", salesMan.getPass());

    }

    @Test
    public void createProduct() throws Exception {
    }

    @Test
    public void createTerminal() throws Exception {
    }

    @Test
    public void createBill() throws Exception {
    }

    @Test
    public void addProductToBill() throws Exception {
    }

    @Test
    public void changeProductToBill() throws Exception {

        Bill currentBill = this.appDB.getBillsTerminal(this.terminal).get(0);

        int oldQuantity = this.appDB.findProductBillByCode(currentBill, 1).getProductQuontity();

        int newQuantity = 6;

        this.appDB.changeProductToBill(currentBill, 1, newQuantity);

        Assert.assertNotEquals(oldQuantity, newQuantity);

    }

    @Test
    public void updateAmountPriceToBill() throws Exception {
    }

    @Test
    public void getQuantityBillsTerminal() throws Exception {
        int quantity = this.appDB.terminalController.getQuantityBillsTerminal(this.terminal);

        this.appDB.remove(this.appDB.terminalController.getBillsTerminal(this.terminal).get(0));

        Assert.assertEquals(quantity, this.appDB.getQuantityBillsTerminal(this.terminal) + 1);

    }

    @Test
    public void saveClosedBill() throws Exception {
    }

    @Test
    public void getBillsTerminal() throws Exception {
    }

    @Test
    public void getProductsPrice() throws Exception {
    }

}