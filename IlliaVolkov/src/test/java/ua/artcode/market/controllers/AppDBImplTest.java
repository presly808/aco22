package ua.artcode.market.controllers;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ua.artcode.market.factory.MarketFactory;
import ua.artcode.market.model.Bill;
import ua.artcode.market.model.SalesMan;
import ua.artcode.market.model.Terminal;
import ua.artcode.market.proxy.MarketProxy;

import java.util.List;

import static org.junit.Assert.*;

public class AppDBImplTest {

    private AppDBImpl appDB;
    private Terminal terminal;

    @Before
    public void initTest() throws Exception {

        MarketProxy marketProxy = MarketFactory.createProxy();

        this.appDB = marketProxy.getMiniMarket().currentAppDBImpl;

        appDB.generator.initSalesMans(2);
        appDB.generator.initProductsPrice(3);

        this.terminal = appDB.createTerminal();
        this.appDB.terminalController.setAutorizedSalesMan(terminal, appDB.getSalesManList().get(0));

        appDB.generator.initBill(this.terminal, appDB.getProductsPrice().size());

        String PriceOfProductsForPrint = appDB.statistics.getPriceOfProductsForPrint(appDB.getProductsPrice());
        String BillProductsForPrint = appDB.statistics.getBillProductsForPrint(appDB.getBillsTerminal(terminal).get(0));
        String BillHeadInfoForPrint = appDB.statistics.getBillHeadInfoForPrint(appDB.getBillsTerminal(terminal).get(0));
    }

    @Test
    public void remove() throws Exception {

        List<Bill> billList = appDB.getBillsTerminal(terminal);

        int sizeBillList = billList.size();

        appDB.remove(billList.get(0));

        Assert.assertEquals(sizeBillList, appDB.getBillsTerminal(this.terminal).size()+1);
    }

    @Test
    public void getAll() throws Exception {
        List<? extends Object> billList = appDB.getAll(Bill.class);

        int sizeBillList = billList.size();

        Assert.assertEquals(sizeBillList, 3);
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

        Bill currentBill = appDB.getBillsTerminal(terminal).get(0);

        int oldQuantity = appDB.findProductBillByCode(currentBill, 1).getProductQuontity();

        int newQuantity = 6;

        this.appDB.changeProductToBill(currentBill, 1, newQuantity);

        Assert.assertNotEquals(oldQuantity, newQuantity);

    }

    @Test
    public void updateAmountPriceToBill() throws Exception {
    }

    @Test
    public void getQuantityBillsTerminal() throws Exception {
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