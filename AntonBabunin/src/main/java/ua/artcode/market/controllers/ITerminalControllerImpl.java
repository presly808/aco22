package ua.artcode.market.controllers;

import ua.artcode.market.interfaces.IAppDb;
import ua.artcode.market.interfaces.ITerminalController;
import ua.artcode.market.models.Bill;
import ua.artcode.market.models.Product;
import ua.artcode.market.models.Terminal;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class ITerminalControllerImpl implements ITerminalController {

    private IAppDb iAppDb;

    public ITerminalControllerImpl(IAppDb iAppDb) {
        this.iAppDb = iAppDb;
    }

    public IAppDb getiAppDb() {
        return iAppDb;
    }

    @Override
    public Bill createBill() {
        Bill bill = new Bill();

        Bill bill1 = iAppDb.saveBill(bill);

        return bill1;
    }

    @Override
    public Bill addProduct(int billId, Product product) {
        Bill bill = iAppDb.findBillById(billId);

        if (bill == null) return null;

        if (!bill.getProductsMap().containsKey(product)) {
            bill.getProductsMap().put(product, 1);
            return bill;
        }

        bill.getProductsMap().replace(product,
                bill.getProductsMap().get(product) + 1);

        iAppDb.update(bill);

        return bill;
    }

    @Override
    public List<Bill> getAllBills() {
        return iAppDb.getAllBills();
    }

    @Override
    public double calculateAmountPrice(Bill bill) {
        double amountPrice = 0.0;
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
    public Bill closeBill(int id) {

        Bill bill = iAppDb.findBillById(id);
        bill.setCloseTime(LocalDateTime.now());
        iAppDb.update(bill);
        return bill;
    }
}
