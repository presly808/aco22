package week1.controller;

import week1.interfaces.IAppDB;
import week1.interfaces.ITerminalController;
import week1.model.Bill;
import week1.model.Product;
import week1.model.Seller;
import week1.model.Statistic;

import java.time.LocalDateTime;
import java.util.List;

public class ITerminalControllerImpl implements ITerminalController {

    private IAppDB iAppDB;

    public ITerminalControllerImpl(IAppDB iAppDB) {
        this.iAppDB = iAppDB;
    }

    @Override
    public boolean login(String login, String password) {

        iAppDB.setCurrentSeller(-1);

        if ((login == null || password == null) ||
                (login.equals("") || password.equals(""))
                || iAppDB.getAllSellers().size() == 0) return false;

        int sellerId = iAppDB.getAllSellers().
                indexOf(iAppDB.findBySellerLoginAndPassword(login, password));

        if (sellerId == -1) {
            System.out.println("There aren't any sellers with such login\\pass!");
            return false;
        }

        iAppDB.setCurrentSeller(sellerId);
        return true;
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
        iAppDB.updateBill(bill);

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

        iAppDB.updateBill(bill);

        return bill;
    }

    @Override
    public Bill findBillById(int billId) {
        return iAppDB.findByBillId(billId);
    }

    @Override
    public Seller findSellerByLoginOrFullName(String loginOrFullName) {
        return iAppDB.findBySellerLoginOrFullName(loginOrFullName);
    }

    @Override
    public Seller[] getTopNOfSalesman(int quantityOfBestSellers) {
        return new Seller[0];
    }

    @Override
    public Statistic doSomeStatisticStuff() {
        return null;
    }
}
