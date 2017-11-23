package ua.artcode.market.controllers;

import ua.artcode.market.interfaces.IAppDb;
import ua.artcode.market.interfaces.ITerminalController;
import ua.artcode.market.models.Bill;
import ua.artcode.market.models.Product;
import ua.artcode.market.models.Salesman;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class ITerminalControllerImpl implements ITerminalController {

//    private static int terminalID = 0;
    private IAppDb iAppDb;

    public ITerminalControllerImpl(IAppDb iAppDb) {
        this.iAppDb = iAppDb;
    }

    public IAppDb getiAppDb() {
        return iAppDb;
    }

    @Override
    public Salesman createSalesman(String fullName, String login,
                                   String password) throws IOException {
        return iAppDb.createSalesman(fullName, login, password);
    }

    @Override
    public Salesman login(String login, String password) throws IOException {
        return iAppDb.login(login, password);
    }

    @Override
    public Salesman logout(Salesman salesman) throws IOException {
        return iAppDb.logout(salesman);
    }

    @Override
    public Salesman findSalesmanByLogin(String login) {
        return iAppDb.findSalesmanByLogin(login);
    }

    @Override
    public Bill createBill() throws IOException {
        Bill bill = new Bill();

        Bill bill1 = iAppDb.saveBill(bill);

        return bill1;
    }

    @Override
    public Bill addProduct(int billId, Product product) throws IOException {
        Bill bill = iAppDb.findBillById(billId);

        if (bill == null || product == null) return null;
        if (!bill.getProductsMap().containsKey(product)) {
            bill.getProductsMap().put(product, 1);
            bill.setAmountPrice(calculateAmountPrice(bill));
            iAppDb.getAllProducts().replace(product,
                    iAppDb.getAllProducts().get(product) - 1);
            return bill;
        }

        bill.getProductsMap().replace(product,
                bill.getProductsMap().get(product) + 1);
        bill.setAmountPrice(calculateAmountPrice(bill));
        iAppDb.getAllProducts().replace(product,
                iAppDb.getAllProducts().get(product) - 1);
        iAppDb.update(bill);

        return bill;
    }

    @Override
    public List<Bill> getAllBills() {
        return iAppDb.getBills();
    }

    @Override
    public double calculateAmountPrice(Bill bill) {

        double amountPrice = 0.0;
        if (bill == null || bill.getProductsMap() == null ||
                bill.getProductsMap().isEmpty()) return amountPrice;
        for (Map.Entry<Product, Integer> pair :
                bill.getProductsMap().entrySet()) {
            amountPrice += pair.getKey().getPrice() * pair.getValue();
        }
        return amountPrice;
    }

    @Override
    public String prinBill(Bill bill) {
        return bill.toString();
    }

    @Override
    public Bill closeBill(int id) throws IOException {

        Bill bill = iAppDb.findBillById(id);
        bill.setCloseTime(LocalDateTime.now());
        iAppDb.update(bill);
        return bill;
    }




}
