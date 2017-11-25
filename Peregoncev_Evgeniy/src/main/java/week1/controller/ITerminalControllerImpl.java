package week1.controller;

import week1.comparators.SalesmanSoldProductComparator;
import week1.interfaces.IAppDb;
import week1.interfaces.ITerminalController;
import week1.model.Bill;
import week1.model.Product;
import week1.model.Salesman;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by ENIAC on 10.11.2017.
 */
public class ITerminalControllerImpl implements ITerminalController {

    private IAppDb iAppDb;

    public ITerminalControllerImpl(IAppDb iAppDb) {
        this.iAppDb = iAppDb;
    }

    private int currentSalesmanIndex = -1;

    public int getCurrentSalesmanIndex() {
        return currentSalesmanIndex;
    }

    public void setCurrentSalesmanIndex(int currentSalesmanIndex) {
        this.currentSalesmanIndex = currentSalesmanIndex;
    }

    //Methods

    @Override
    public boolean login(String login, String pass) {
        if (login == null || login.isEmpty() || pass == null || pass.isEmpty()) {
            System.out.println("write true login/pass");
        } else if (iAppDb == null) {
            System.out.println("wrong salesman database");
        }

            for (Salesman salesman : iAppDb.getAllSalesMans()) {

            if ((salesman.getLogin().equals(login)) && (salesman.getPass().equals(pass))) {
                setCurrentSalesmanIndex(salesman.getId());
                System.out.println("Hello " + salesman.getName());
            }
        }
        if (currentSalesmanIndex == -1) System.out.println("wrong login/pass");
        return Boolean.parseBoolean(null);
    }

    @Override
    public Bill createBill() {

        Bill bill = new Bill();

        bill.setSalesman(iAppDb.getAllSalesMans().get(getCurrentSalesmanIndex()));

        Bill billWithId = iAppDb.saveBill(bill);

        return billWithId;
    }

    @Override
    public IAppDb getDb() {
        return iAppDb;
    }

    @Override
    public Bill findBillById(int billId) {

        return iAppDb.findByBillId(billId);
    }

    @Override
    public Salesman findSalesmanByLogin(String login) {

        return iAppDb.findSalesmanByLogin(login);
    }

    @Override
    public Salesman getTopOfSalesmans() {
        iAppDb.getAllSalesMans().sort(new SalesmanSoldProductComparator());
        return iAppDb.getAllSalesMans().get(iAppDb.getAllSalesMans().size() - 1);
    }

    @Override
    public Bill addProduct(int billId, Product product) {
        Bill bill = iAppDb.findByBillId(billId);

        if (bill == null) {
            System.out.println("no bill found");
            return null;
        } else if (bill.isClosed()) {
            System.out.println("bill is closed. cant add product to closed bill.");
            return null;
        } else {

            bill.getProductList().add(product);
            bill.setAmountPrice(bill.getAmountPrice() + product.getPrice());

            iAppDb.update(bill);

            System.out.println("product " + product.getName()
                    + " was added to bill with id " + product.getId());
            return bill;
        }
    }

    @Override
    public List<Bill> getAllBills() {
        return iAppDb.getAllBills();
    }

    @Override
    public Bill closeBill(int id) {
        Bill bill = iAppDb.findByBillId(id);
        bill.getTime().setCloseTime(bill.getTime().printTime());
        bill.setClosed(true);
        bill.getSalesman().setCountSoldProduct
                (bill.getSalesman().getCountSoldProduct() +
                (bill.getProductList().size()));

        iAppDb.update(bill);

        return bill;
    }

    @Override
    public List<Product> getAllProducts() {
        return iAppDb.getAllProducts();
    }

    @Override
    public List<Bill> filterForBills(String start, String end, Comparator<Bill> comparator) {

        List<Bill> billList = new ArrayList<>();

        for (Bill bill : iAppDb.getAllBills()) {
            if (bill.getTime().getCloseTime().compareTo(start) > 0 &&
                    bill.getTime().getCloseTime().compareTo(end) < 0) {
                billList.add(bill);
            }
        }
        billList.sort(comparator);
        return billList;
    }


}
