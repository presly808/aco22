package ua.artcode.market.controller;

import ua.artcode.market.models.Bill;
import ua.artcode.market.models.Product;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by serhii on 19.11.17.
 */
public class ITerminalControllerImpl implements ITerminalController {

    private IAppDb iAppDb;

    public ITerminalControllerImpl(IAppDb iAppDb) {
        this.iAppDb = iAppDb;
    }

    @Override
    public Bill createBill() {

        Bill bill = new Bill();

        Bill billWihtId = iAppDb.saveBill(bill);

        return billWihtId;
    }

    @Override
    public Bill addProduct(int billId, Product product) {
        Bill bill = iAppDb.findByBillId(billId);

        if(bill == null){
            System.out.println("no bill found");
            return null;
        }

        boolean add = bill.getProductList().add(product);

        iAppDb.update(bill);

        return bill;
    }

    @Override
    public List<Bill> getAllBills() {
        return iAppDb.getAllBills();
    }

    @Override
    public Bill closeBill(int id) {
        Bill bill = iAppDb.findByBillId(id);
        bill.setClosed(true);
        bill.setCloseTime(LocalDateTime.now());

        iAppDb.update(bill);


        return bill;
    }
}
