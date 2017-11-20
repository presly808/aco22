package week1.controller;

import week1.interfaces.IAppDB;
import week1.model.Bill;
import week1.model.Product;

import java.time.LocalDateTime;
import java.util.List;

public class ITerminalControllerImpl implements week1.interfaces.ITerminalController {

    private IAppDB iAppDB;

    public ITerminalControllerImpl(IAppDB iAppDB) {
        this.iAppDB = iAppDB;
    }

    @Override
    public Bill createBill() {

        Bill bill = new Bill();

        Bill billWithId = iAppDB.saveBill(bill);

        return billWithId;
    }

    @Override
    public Bill addProduct(int billId, Product product) {

        Bill bill = iAppDB.findByBillId(billId);

        if (bill == null) {
            System.out.println("Not found a bill with such id");
            return null;
        }

        boolean add = bill.getProductList().add(product);
        iAppDB.update(bill);

        return bill;
    }

    @Override
    public List<Bill> getAllBills() {
        return iAppDB.getAllBills();
    }

    @Override
    public Bill closeBill(int billId) {
        Bill bill = iAppDB.findByBillId(billId);

        if (bill == null) {
            System.out.println("Not found a bill with such id");
            return null;
        }

        bill.setClosed(true);
        bill.setCloseTime(LocalDateTime.now());

        iAppDB.update(bill);

        return bill;
    }
}
